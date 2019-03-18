;
const MyValidator = {
	validate : function(targetObj, autoAlertTF) {
		if (_.toLower(targetObj.prop("tagName")) === 'form') {
			return MyValidator.validateForm(targetObj, autoAlertTF);
		} else {
			return MyValidator.validateElement(targetObj, autoAlertTF);
		}
	},

	validateForm : function(formObj, autoAlertTF) {
		let validForm = formObj.parsley();
		if (validForm === undefined) {
			// 개발용
			alert('개발오류 : 잘못된 validate 대상');
			return "";
		}
		for ( let i in validForm.fields) {
			let result = MyValidator.validateElement(validForm.fields[i].$element[0], autoAlertTF);
			if (result !== null) {
				return result;
			}
		}
		return null;
	},

	validateElement : function(elementObj, autoAlertTF) {
		if ($(elementObj).length === 0) {
			return null;
		}
		let validElement = $(elementObj).parsley();
		if (validElement === undefined) {
			// 개발용
			alert('개발오류 : 잘못된 validate 대상');
			return "";
		}
		if (!validElement.isValid()) {
			$.when().done(function() {
				if (autoAlertTF !== false) {
					if (!!$(validElement.$element[0]).data("valid-message")) {
						alert($(validElement.$element[0]).data("valid-message"));
					} else {
						alert(validElement.$element[0].title + "은(는) " + validElement.getErrorsMessages());
					}
				}
			}).done(function() {
				$(validElement.$element).trigger('focus');
			});

			return !!validElement.$element[0].id ? validElement.$element[0].id : validElement.$element[0].name;
		}
		return null;
	}
};
