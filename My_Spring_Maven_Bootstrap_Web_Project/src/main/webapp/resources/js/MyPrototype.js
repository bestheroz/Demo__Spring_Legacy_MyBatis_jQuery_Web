String.prototype.replaceAll = function(src, dest) {
	return this.split(src).join(dest);
};