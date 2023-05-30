import { SuperComponent, RelationsOptions } from '../common/src/index';
export default class BackTop extends SuperComponent {
    externalClasses: string[];
    options: {
        multipleSlots: boolean;
    };
    properties: import("./type").TdBackTopProps;
    relations: RelationsOptions;
    data: {
        prefix: string;
        classPrefix: string;
        _icon: any;
    };
    observers: {
        icon(): void;
    };
    lifetimes: {
        ready(): void;
    };
    methods: {
        setIcon(v: any): void;
        toTop(): void;
    };
}
