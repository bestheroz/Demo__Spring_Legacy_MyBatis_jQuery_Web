package com.github.bestheroz.standard.context.db.transaction.support;

import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lee HeeGu <elf_hazard@naver.com>,<elfHazard@gmail.com>
 */
public class WiredTransactionStatus implements TransactionStatus {
    List<TransactionStatus> statusList = new ArrayList<>();

    private boolean newTransaction;

    private boolean rollbackOnly;

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.transaction.TransactionStatus#hasSavepoint()
     */
    @Override
    public boolean hasSavepoint() {
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.transaction.TransactionStatus#isCompleted()
     */
    @Override
    public boolean isCompleted() {
        for (final TransactionStatus status : this.statusList) {
            if (!status.isRollbackOnly()) {
                return false;
            }
        }

        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.transaction.TransactionStatus#isNewTransaction()
     */
    @Override
    public boolean isNewTransaction() {
        return this.newTransaction;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.transaction.TransactionStatus#isRollbackOnly()
     */
    @Override
    public boolean isRollbackOnly() {
        return this.isLocalRollbackOnly() || this.isGlobalRollbackOnly();
    }

    /**
     * Determine the rollback-only flag via checking this TransactionStatus.
     * <p>
     * Will only return "true" if the application called <code>setRollbackOnly</code> on this TransactionStatus object.
     */
    public boolean isLocalRollbackOnly() {
        return this.rollbackOnly;
    }

    /**
     * Template method for determining the global rollback-only flag of the underlying transaction, if any.
     */
    public boolean isGlobalRollbackOnly() {
        for (final TransactionStatus status : this.statusList) {
            if (status.isRollbackOnly()) {
                return true;
            }
        }

        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.transaction.TransactionStatus#setRollbackOnly()
     */
    @Override
    public void setRollbackOnly() {
        this.rollbackOnly = true;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.transaction.SavepointManager#createSavepoint()
     */
    @Override
    public Object createSavepoint() throws TransactionException {
        return new Object();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.transaction.SavepointManager#releaseSavepoint(java.lang.Object)
     */
    @Override
    public void releaseSavepoint(final Object savepoint) throws TransactionException {

    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.transaction.SavepointManager#rollbackToSavepoint(java.lang.Object)
     */
    @Override
    public void rollbackToSavepoint(final Object savepoint) throws TransactionException {

    }

    /**
     * 새로운 TransactionStatus를 추가합니다.
     *
     * @param subStatus transaction status 정보
     */
    public void addSubTransactionStatus(final TransactionStatus subStatus) {
        this.statusList.add(0, subStatus);
    }

    /**
     * 원하는 위치에 있는 transaction status 정보를 알려줍니다.
     *
     * @param index status가 있는 위치. 0-base
     * @return transaction status 정보
     */
    public TransactionStatus getTransactionStatus(final int index) {
        return this.statusList.get(index);
    }

    @Override
    public void flush() {
    }

}