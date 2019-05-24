seajs.config({
    comboExcludes: /.*/,
    paths: {sceneIndex: "//" + window.location.host + "/js/sceneIndex"}
}), seajs.use(["sceneIndex/drop.js"], function (drop) {
    drop()
});
