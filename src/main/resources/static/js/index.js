/**
 * @author        gebilaoxiong
 * @email         gebilaoxiong@gmail.com
 * @date          2017-02-21 05:08:35
 * @description   阿拉蕾seajs-config
 */
seajs.config({
  alias: {
    "widget": 'arale/widget/1.1.1/widget',
    "position": 'arale/position/1.0.1/position',
    "cookie": 'arale/cookie/1.0.2/cookie',
    "sticky": 'arale/sticky/1.3.1/sticky',
    "tabs": 'arale/switchable/1.0.3/tabs',
    "slide": 'arale/switchable/1.0.3/slide',
    "accordion": 'arale/switchable/1.0.3/accordion',
    "carousel": 'arale/switchable/1.0.3/carousel',
    "overlay": 'arale/overlay/1.1.3/overlay',
    "mask": 'arale/overlay/1.1.3/mask',
    "dialog": 'arale/dialog/1.3.0/dialog',
    "confirmbox": 'arale/dialog/1.3.0/confirmbox',
    "events": 'arale/events/1.1.0/events',
    "validator": 'arale/validator/0.9.7/validator',
    "autocomplete": 'arale/autocomplete/1.2.3/autocomplete'
  },
  paths: {
    "arale": ZBJInfo.staticLibURI + '/rake-component/fe-common/utopia-arale',
    "gallery": ZBJInfo.staticLibURI + '/rake-component/fe-common/utopia-gallery'
  }
});

define('hack$', [], function () {
  return window.$;
});
seajs.use('hack$');

seajs.cache[seajs.resolve('$')] =
  seajs.cache[seajs.resolve('$-debug')] =
  seajs.cache[seajs.resolve('jquery')] =
  seajs.cache[seajs.resolve('jquery-debug')] =
  seajs.cache[seajs.resolve('hack$')];