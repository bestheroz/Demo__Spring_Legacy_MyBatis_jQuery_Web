;
const MyAjax = {
	defaultOptions : {
		type : 'POST',
		async : true,
		cache : false,
		dataType : 'json',
		contentType : 'application/x-www-form-urlencoded; charset=UTF-8',
		successMessage : undefined,
		autoResultFunctionTF : false,
		// autoAjaxErrorFunctionTF : 오픈 후 false로... true는 개발용
		autoAjaxErrorFunctionTF : true,
		// 아래는 getSelectOptions() 옵션
		placeholder : '선택하세요.',
		withCodeTF : false,
		value : 'value',
		label : 'label'
	},

	excute : function(paramUrl, paramData, paramOption) {
		let chk = MyAjax.checkMaxPostSize(paramData);
		if (chk !== null) {
			return chk;
		}
		let promise;
		Pace.track(function() {
			Pace.restart();
			let options = MyCommon.getOptions(MyAjax.defaultOptions, paramOption);
			options['url'] = paramUrl;
			options['data'] = paramData;
			promise = $.ajax(options).done(function(response) {
				MyAjax.doneDefaultFunction(response, options);
			}).done(function(response) {
				MyAjax.doneAutoResultFunction(response, options);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				MyAjax.failFunction(jqXHR, textStatus, errorThrown, options);
			});
		});
		return promise;
	},
	excuteWithFile : function(paramUrl, paramFormData, paramOption) {
		let chk = MyAjax.checkMaxPostSize(paramFormData);
		if (chk !== null) {
			return chk;
		}
		let promise;
		Pace.track(function() {
			Pace.restart();
			let options = MyCommon.getOptions(MyAjax.defaultOptions, paramOption);
			options['url'] = paramUrl;
			options['data'] = paramFormData;
			options['processData'] = false;
			options['contentType'] = false;
			promise = $.ajax(options).done(function(response) {
				MyAjax.doneDefaultFunction(response, options);
			}).done(function(response) {
				MyAjax.doneAutoResultFunction(response, options);
			}).fail(function(jqXHR, textStatus, errorThrown) {
				MyAjax.failFunction(jqXHR, textStatus, errorThrown, options);
			});
		});
		return promise;
	},

	getSelectOptions : function(paramTargetObj, paramUrl, paramData, paramOption) {
		let chk = MyAjax.checkMaxPostSize(paramData);
		if (chk !== null) {
			return chk;
		}
		let promise;
		Pace.track(function() {
			Pace.restart();
			let options = MyCommon.getOptions(MyAjax.defaultOptions, paramOption);
			options['url'] = paramUrl;
			options['data'] = paramData;
			promise = $.ajax(options).done(function(response) {
				MyAjax.doneDefaultFunction(response, options)
			}).done(function(response) {
				$(paramTargetObj).html('');
				if (!$(paramTargetObj).prop('required') || (MyCommon.isNotEmpty(paramOption) && MyCommon.isNotEmpty(paramOption.placeholder))) {
					$(paramTargetObj).append("<option value=''>" + options.placeholder + "</option>");
				}
				$.each(response, function(index, value) {
					if (options.withCodeTF) {
						$(paramTargetObj).append("<option value='" + value[options.value] + "'>" + "[" + value[options.value] + "]" + value[options.label] + "</option>");
					} else {
						$(paramTargetObj).append("<option value='" + value[options.value] + "'>" + value[options.label] + "</option>");
					}
				});
			}).fail(function(jqXHR, textStatus, errorThrown) {
				MyAjax.failFunction(jqXHR, textStatus, errorThrown, options);
			});
		});
		return promise;
	},
	downloadFile : function(paramUrl, paramData, paramOption) {
		let chk = MyAjax.checkMaxPostSize(paramData);
		if (chk !== null) {
			return chk;
		}
		let promise;
		Pace.track(function() {
			Pace.restart();
			let options = MyCommon.getOptions(MyAjax.defaultOptions, paramOption);
			promise = $.fileDownload(paramUrl, {
				httpMethod : options.type,
				data : paramData,
				prepareCallback : function(url) {
					//
				}
			}).fail(function(responseHtml, url, error) {
				console.warn("responseHtml : " + responseHtml + "\nurl : " + url + "\nerror : " + error);
				let response = MyAjax.getJsonFromWrappedInPreTag(responseHtml);
				if (MyCommon.isNotEmpty(response.responseMessage)) {
					alert(response.responseMessage);
				} else {
					alert('파일이 존재하지 않습니다.');
				}
			});
		});
		return promise;
	},
	checkMaxPostSize : function(paramData) {
		if (MyCommon.isNotEmpty(paramData) && JSON.stringify(paramData).length > 8388608) { // 현재8MB : server.xml maxPoolSize값 참고
			alert('입력하신 값이 너무 많아서 문제가 발생하고 있습니다. 입력하신 값을 줄여주세요.');
			return $.Deferred().reject('입력하신 값이 너무 많아서 문제가 발생하고 있습니다. 입력하신 값을 줄여주세요.').promise();
		} else {
			return null;
		}
	},
	// 가끔 ajax 에러발생시 <pre>를 포함한 데이터가 넘어올 때가 있다.
	getJsonFromWrappedInPreTag : function(paramHtml) {
		if (!!~paramHtml.toString().indexOf('<pre ') || !!~paramHtml.toString().indexOf('<pre>')) {
			try {
				return JSON.parse(paramHtml.substring(paramHtml.indexOf('>') + 1, paramHtml.indexOf('</')));
			} catch (e) {
				console.warn(e);
			}
		}
		return paramHtml;
	},
	doneDefaultFunction : function(response, options) {
		if (options.dataType === 'json') {
			response = MyAjax.getJsonFromWrappedInPreTag(response);
		}
		if (response.responseCode === 'E011') {
			if (confirm("페이지 시간초과\n페이지를 새로고침하여 로그인페이지로 안내합니다.")) {
				window.location.reload(true);
			}
			return false;
		}
	},
	doneAutoResultFunction : function(response, options) {
		if (options.autoResultFunctionTF) {
			if (MyCommon.isNotEmpty(response.responseCode) && MyCommon.isNotEmpty(response.responseMessage)) {
				let message = null;
				if (MyCommon.isNotEmpty(options.successMessage) && _.startsWith(response.responseCode, 'S')) {
					message = options.successMessage;
				} else {
					message = response.responseMessage;
				}
				if (MyCommon.isNotEmpty(response.additionalMessage)) {
					message += "\n" + response.additionalMessage;
				}
				alert(message);
			}
		}
	},
	failFunction : function(jqXHR, textStatus, errorThrown, options) {
		console.warn("jqXHR : " + jqXHR + "\ntextStatus : " + textStatus + "\nerrorThrown : " + errorThrown);
		if (textStatus === "timeout") {
			console.warn("요청시간 초과");
			if (options.autoAjaxErrorFunctionTF) {
				alert("요청시간 초과");
			}
		} else {
			console.warn("시스템에러: return null 또는 HTTP 500 ");
			if (options.autoAjaxErrorFunctionTF) {
				alert("잠시후 다시 시도해주세요.");
			}
		}
	},
	drawHtml : function(targetObj, mode, html) {
		return $.when().done(function() {
			if (mode === 'html') {
				MyHandlebars.removeTemplateScript(targetObj);
				$(targetObj).html('').html(html);
			} else if (mode === 'append') {
				$(targetObj).append(html);
			} else if (mode === 'appendTo') {
				$(targetObj).appendTo(html);
			} else if (mode === 'prepend') {
				$(targetObj).prepend(html);
			} else if (mode === 'prependTo') {
				$(targetObj).prependTo(html);
			} else if (mode === 'after') {
				$(targetObj).after(html);
			} else if (mode === 'before') {
				$(targetObj).before(html);
			}
		}).done(function() {
			MyAjax.initView(targetObj);
			MyAjax.removeDeplicationModal(targetObj);
		});
	},
	initView : function(targetObj) {
		targetObj = targetObj || $(document);

		// Handlebars
		try {
			MyHandlebars.setTemplateScript(targetObj, true);
		} catch (e) {
			// console.warn(e);
		}
		// MaxLength
		try {
			MyMaxlength.init($(targetObj));
		} catch (e) {
			// console.warn(e);
		}
		// MyDatetimePicker
		try {
			MyDatetimePicker.init($(targetObj));
		} catch (e) {
			// console.warn(e);
		}
		// MyTextEditor
		try {
			MyTextEditor.init($(targetObj));
		} catch (e) {
			// console.warn(e);
		}
	},
	removeDeplicationModal : function(targetObj) {
		if (!targetObj) {
			return;
		}
		// ajax를 통해 가져온 (기존)중복된 Model 제거
		try {
			$.each($(targetObj).find('.jmodal'), function(index, value) {
				if ($('body > #' + $(value).prop('id')) !== $(value)) {
					$('body > #' + $(value).prop('id')).remove();
				}
			});
		} catch (e) {
			console.warn(e);
		}
	}
};
jQuery.ajaxSettings.traditional = true;