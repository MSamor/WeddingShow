/// <reference types="miniprogram-api-typings" />
/// <reference types="miniprogram-api-typings" />
declare type IPageScrollOption = WechatMiniprogram.Page.IPageScrollOption;
declare type Scroller = (this: WechatMiniprogram.Component.TrivialInstance, event?: IPageScrollOption) => void;
declare const _default: (scroller: Scroller) => string;
export default _default;
