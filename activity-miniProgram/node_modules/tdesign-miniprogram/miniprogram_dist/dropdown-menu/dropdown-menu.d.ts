import { RelationsOptions, SuperComponent } from '../common/src/index';
import type { TdDropdownMenuProps } from './type';
export interface DropdownMenuProps extends TdDropdownMenuProps {
}
export default class DropdownMenu extends SuperComponent {
    externalClasses: string[];
    properties: TdDropdownMenuProps;
    nodes: any;
    data: {
        prefix: string;
        classPrefix: string;
        menus: any;
        activeIdx: number;
        bottom: number;
    };
    relations: RelationsOptions;
    lifetimes: {
        ready(): void;
    };
    methods: {
        toggle(index: number): void;
        getAllItems(): void;
        handleToggle(e: WechatMiniprogram.BaseEvent): void;
    };
}
