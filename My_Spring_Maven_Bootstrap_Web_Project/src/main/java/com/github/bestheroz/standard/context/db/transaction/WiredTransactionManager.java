package com.github.bestheroz.standard.context.db.transaction;

import com.github.bestheroz.standard.context.db.transaction.support.WiredTransactionStatus;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.List;

/**
 * <pre>
 * 여러 개의 {@link org.springframework.transaction.PlatformTransactionManager}를
 * 다룰 수 있도록 만들어진 클래스입니다.
 * <p>
 * 하는 일은 매우 단순하여, transaction을 시작하고, commit을 실행하도록 하거나,
 * rollback을 실행하도록 여러 TransactionManager에게 명령을 내리는 역할을 합니다.
 * DataSource를 다루는 명령이 예외를 발생시키지 않는 한 모든 TransactionManager는
 * 무사히 commit을 실행할 수 있게 됩니다.
 * 만약 예외가 발생된다면 이 TransactionManager는 모든 TransactionManager에게
 * rollback을 수행하도록 유도합니다. 이는 대부분의 상황에서
 * two-phase commit process와 매우 유사하게 동작을 하는 것처럼 보일 것입니다.
 * <p>
 * 이 class는 http://www.javaworld.com/javaworld/jw-01-2009/jw-01-spring-transactions.html에
 * 기술된 내용과 거의 유사하지만, 하나의 transaction에서 오류가 발생하면
 * 모든 transaction을 rollback시켜주는 점이 다릅니다.
 * </pre>
 *
 * @author Lee HeeGu <elf_hazard@naver.com>,<elfHazard@gmail.com>
 */
public class WiredTransactionManager implements PlatformTransactionManager, InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected List<PlatformTransactionManager> transactionManagers;
    // protected List<PlatformTransactionManager> reversed;

    /**
     * TransactionManager 목록을 설정합니다. 이 때 commit이나 rollback을 위해서는 transaction을 시작한 순서와는 반대로 해야 하기 때문에 reverse된 목록이 필요합니다.
     *
     * @param transactionManagers TransactionManager 목록
     */
    public void setTransactionManagers(final List<PlatformTransactionManager> transactionManagers) {
        this.transactionManagers = transactionManagers;
        // this.reversed = new ArrayList<PlatformTransactionManager>(transactionManagers);
        // Collections.reverse(reversed);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.transactionManagers == null || this.transactionManagers.size() == 0) {
            this.logger.warn(ExceptionUtils.getStackTrace(new IllegalArgumentException("Property 'transactionManagers' is required")));
            throw new IllegalArgumentException("Property 'transactionManagers' is required");
        }
    }

    /**
     * 모든 TransactionManager들에게 commit을 시작하라는 명령을 내립니다.
     * <p>
     * commit을 수행하기 전 rollback이 되는 transaction이 있으면 모든 transaction을 rollback이 수행되도록 합니다.
     *
     * @see org.springframework.transaction.PlatformTransactionManager#commit(org.springframework.transaction.TransactionStatus)
     */
    @Override
    public void commit(final TransactionStatus status) throws TransactionException {
        final WiredTransactionStatus wiredTransactionStatus = (WiredTransactionStatus) status;

        if (wiredTransactionStatus.isRollbackOnly()) {
            this.rollback(wiredTransactionStatus);
            return;
        }

        PlatformTransactionManager subTransactionManager;

        for (int index = 0; index < this.transactionManagers.size(); index = index + 1) {
            subTransactionManager = this.transactionManagers.get(index);
            subTransactionManager.commit(wiredTransactionStatus.getTransactionStatus(index));
        }
    }

    /**
     * transaction을 시작합니다.
     * <p>
     * 모든 TransactionManager들에게 transaction을 시작하라는 명령을 내립니다. 각각의 PlatformTransactionManager는 트랜젝션에 대한 상태를 나타내는 객체를 리턴하게 되는데, 이 정보들을 담기 위해 {@link WiredTransactionStatus}를 생성한 후, 각각의
     * TransactionManager들이 생성한 transaction status 정보를 담아 {@link TransactionInterceptor}로 넘겨줍니다.
     *
     * @see org.springframework.transaction.PlatformTransactionManager#getTransaction(org.springframework.transaction.TransactionDefinition)
     */
    @Override
    public TransactionStatus getTransaction(final TransactionDefinition definition) throws TransactionException {
        final WiredTransactionStatus status = new WiredTransactionStatus();

        for (final PlatformTransactionManager transactionManager : this.transactionManagers) {
            final TransactionStatus subStatus = transactionManager.getTransaction(definition);
            status.addSubTransactionStatus(subStatus);
        }

        return status;
    }

    /**
     * 모든 TransactionManager들에게 rollback을 시도하라는 명령을 내립니다.
     * <p>
     * n번째 rollback이 실패해도 나머지 rollback이 무사히 수행될 수 있도록 하위 TransactionManager에서 실행하는 rollback 메소드는 try문으로 보호되어 있습니다.
     *
     * @see org.springframework.transaction.PlatformTransactionManager#rollback(org.springframework.transaction.TransactionStatus)
     */
    @Override
    public void rollback(final TransactionStatus status) throws TransactionException {
        final WiredTransactionStatus wiredTransactionStatus = (WiredTransactionStatus) status;

        PlatformTransactionManager subTransactionManager;
        TransactionException reserved = null;

        for (int index = 0; index < this.transactionManagers.size(); index = index + 1) {
            subTransactionManager = this.transactionManagers.get(index);

            try {
                subTransactionManager.rollback(wiredTransactionStatus.getTransactionStatus(index));
            } catch (final TransactionException e) {
                this.logger.warn(ExceptionUtils.getStackTrace(e));
                reserved = e;
            }
        }

        if (reserved != null) {
            this.logger.warn(ExceptionUtils.getStackTrace(reserved));
            throw reserved;
        }
    }

}
