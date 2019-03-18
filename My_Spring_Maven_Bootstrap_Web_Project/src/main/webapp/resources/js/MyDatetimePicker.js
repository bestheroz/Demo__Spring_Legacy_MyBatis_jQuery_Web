;
const MyDatetimePicker = {
	init : function(targetObj) {
		targetObj = targetObj || $(document);
		$(targetObj).find('.datepicker').each(function() {
			MyDatetimePicker.setDatePicker(this);
		});
		$(targetObj).find('.datepicker-yyyymmdd').each(function() {
			MyDatetimePicker.setDatePickerYYYYMMDD(this);
		});
		$(targetObj).find('.datepicker-yyyymm').each(function() {
			MyDatetimePicker.setDatePickerYYYYMM(this);
		});
		$(targetObj).find('.datepicker-yyyy').each(function() {
			MyDatetimePicker.setDatePickerYYYY(this);
		});

		$(targetObj).find('.timepicker').each(function() {
			MyDatetimePicker.setTimePicker(this);
		});
		$(targetObj).find('.timepicker-hhmmss').each(function() {
			MyDatetimePicker.setTimePickerHHmmss(this);
		});
		$(targetObj).find('.timepicker-hhmm').each(function() {
			MyDatetimePicker.setTimePickerHHmm(this);
		});
		$(targetObj).find('.timepicker-hh').each(function() {
			MyDatetimePicker.setTimePickerHH(this);
		});

		$(targetObj).find('.datetimepicker').each(function() {
			MyDatetimePicker.setDateTimePicker(this);
		});
		$(targetObj).find('.datetimepicker-yyyymmddhhmmss').each(function() {
			MyDatetimePicker.setDateTimePickerYYYYMMDDHHmmss(this);
		});
		$(targetObj).find('.datetimepicker-yyyymmddhhmm').each(function() {
			MyDatetimePicker.setDateTimePickerYYYYMMDDHHmm(this);
		});
		$(targetObj).find('.datetimepicker-yyyymmddhh').each(function() {
			MyDatetimePicker.setDateTimePickerYYYYMMDDHH(this);
		});

	},
	setDatePicker : function(targetObj) {
		MyDatetimePicker.setDatePickerYYYYMMDD(targetObj);
		return $(targetObj);
	},
	setDatePickerYYYYMMDD : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				format : 'YYYY-MM-DD'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},
	setDatePickerYYYYMM : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				format : 'YYYY-MM'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},
	setDatePickerYYYY : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				format : 'YYYY'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},

	setTimePicker : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setTimePickerHHmm(targetObj);
		}
		return $(targetObj);
	},
	setTimePickerHHmmss : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				format : 'HH:mm:ss'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},
	setTimePickerHHmm : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				format : 'HH:mm'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},
	setTimePickerHH : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				format : 'HH'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},

	setDateTimePicker : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setDateTimePickerYYYYMMDDHHmm(targetObj);
		}
		return $(targetObj);
	},
	setDateTimePickerYYYYMMDDHHmmss : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				// sideBySide : true,
				format : 'YYYY-MM-DD HH:mm:ss'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},
	setDateTimePickerYYYYMMDDHHmm : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				// sideBySide : true,
				format : 'YYYY-MM-DD HH:mm'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},
	setDateTimePickerYYYYMMDDHH : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				// sideBySide : true,
				format : 'YYYY-MM-DD HH'
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},

	setDateTimePickerDefault : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},

	setDateTimePickerFormat : function(targetObj, paramFormat) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
		} else {
			MyDatetimePicker.setParentPositionRelative(targetObj);
			$(targetObj).datetimepicker({
				format : paramFormat,
			// sideBySide : true
			});
			MyDatetimePicker.setDefaultOption(targetObj);
		}
		return $(targetObj);
	},

	setFromTo : function(fromObj, toObj) {
		if ($(fromObj).length === 0 || $(toObj).length === 0) {
			console.warn('올바르지않은 대상');
			return;
		}
		$(fromObj).on("dp.change", function(e) {
			if (_.isEmpty($(fromObj).val())) {
				$(toObj).data("DateTimePicker").minDate(false);
			} else {
				$(toObj).data("DateTimePicker").minDate($(fromObj).data("DateTimePicker").date());
			}
		});
		$(toObj).on("dp.change", function(e) {
			if (_.isEmpty($(toObj).val())) {
				$(fromObj).data("DateTimePicker").maxDate(false);
			} else {
				$(fromObj).data("DateTimePicker").maxDate($(toObj).data("DateTimePicker").date());
			}
		});
		$(fromObj).trigger('dp.change').trigger('change');
		$(toObj).trigger('dp.change').trigger('change');
	},

	setDatePickerYYYYMMDDWithFromTo : function(fromObj, toObj) {
		if ($(fromObj).length === 0 || $(toObj).length === 0) {
			console.warn('올바르지않은 대상');
			return;
		}
		MyDatetimePicker.setDatePickerYYYYMMDD($(fromObj));
		MyDatetimePicker.setDatePickerYYYYMMDD($(toObj));
		$(fromObj).on("dp.change", function(e) {
			if (_.isEmpty($(fromObj).val())) {
				$(toObj).data("DateTimePicker").minDate(false);
			} else {
				$(toObj).data("DateTimePicker").minDate($(fromObj).data("DateTimePicker").date());
			}
		});
		$(toObj).on("dp.change", function(e) {
			if (_.isEmpty($(toObj).val())) {
				$(fromObj).data("DateTimePicker").maxDate(false);
			} else {
				$(fromObj).data("DateTimePicker").maxDate($(toObj).data("DateTimePicker").date());
			}
		});
		$(fromObj).trigger('dp.change').trigger('change');
		$(toObj).trigger('dp.change').trigger('change');
	},

	// 달력이 모달이나 새로 띄워진 화면에 띄우려면 위치를 지정해줘야한다.
	setParentPositionRelative : function(targetObj) {
		let datetimepickerId = moment().valueOf() + _.random(0, 999);
		$('<span style="position:relative;" data-datetimepicker-id="' + datetimepickerId + '">').insertBefore(targetObj);
		$('span[data-datetimepicker-id="' + datetimepickerId + '"]').css({
			margin : 0,
			padding : 0,
			border : 0
		}).html($(targetObj));
	},

	setDefaultOption : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
			return;
		}
		$(targetObj).data("DateTimePicker").showClear(true).showTodayButton(true).locale(new moment().local('locale')).ignoreReadonly(true).useCurrent(false);
	},
	destroy : function(targetObj) {
		if ($(targetObj).length === 0) {
			console.warn('올바르지않은 대상');
			return;
		}
		$(targetObj).data("DateTimePicker").destroy();
	}
};
$(document).ready(function() {
	MyDatetimePicker.init();
});
