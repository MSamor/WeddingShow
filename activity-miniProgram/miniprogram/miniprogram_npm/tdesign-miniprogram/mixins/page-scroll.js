import { getCurrentPage } from '../common/utils';
const onPageScroll = function (event) {
    const page = getCurrentPage();
    if (!page)
        return;
    const { pageScroller } = page;
    pageScroller.forEach((scroller) => {
        if (typeof scroller === 'function') {
            scroller(event);
        }
    });
};
export default (scroller) => {
    return Behavior({
        attached() {
            const page = getCurrentPage();
            if (!page)
                return;
            const bindScroller = scroller.bind(this);
            if (Array.isArray(page.pageScroller)) {
                page.pageScroller.push(bindScroller);
            }
            else {
                page.pageScroller =
                    typeof page.onPageScroll === 'function' ? [page.onPageScroll.bind(page), bindScroller] : [bindScroller];
            }
            page.onPageScroll = onPageScroll;
        },
        detached() {
            var _a;
            const page = getCurrentPage();
            if (!page)
                return;
            page.pageScroller = ((_a = page.pageScroller) === null || _a === void 0 ? void 0 : _a.filter((item) => item !== scroller)) || [];
        },
    });
};
