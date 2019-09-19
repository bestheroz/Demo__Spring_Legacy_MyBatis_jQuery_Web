String.prototype.replaceAll = (src, dest) => {
    return this.split(src).join(dest);
};