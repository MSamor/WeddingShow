import { SuperComponent, ComponentsOptionsType } from '../common/src/index';
export default class NoticeBar extends SuperComponent {
    externalClasses: string[];
    options: ComponentsOptionsType;
    properties: import("./type").TdNoticeBarProps;
    data: {
        prefix: string;
        classPrefix: string;
        loop: number;
    };
    observers: {
        marquee(val: any): void;
        visible(visible: any): void;
        prefixIcon(prefixIcon: any): void;
        suffixIcon(v: any): void;
        content(): void;
    };
    lifetimes: {
        created(): void;
        detached(): void;
        ready(): void;
    };
    methods: {
        initAnimation(): void;
        startScrollAnimation(isFirstScroll?: boolean): void;
        show(): void;
        clearNoticeBarAnimation(): void;
        setPrefixIcon(v: any): void;
        clickPrefixIcon(): void;
        clickContent(): void;
        clickSuffixIcon(): void;
        clickOperation(): void;
    };
}
