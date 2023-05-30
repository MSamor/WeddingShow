import { SuperComponent, ComponentsOptionsType, RelationsOptions } from '../common/src/index';
export default class CheckBox extends SuperComponent {
    externalClasses: string[];
    behaviors: string[];
    relations: RelationsOptions;
    options: ComponentsOptionsType;
    properties: {
        theme: {
            type: StringConstructor;
            value: string;
        };
        borderless: {
            type: BooleanConstructor;
            value: boolean;
        };
        placement?: {
            type: StringConstructor;
            value?: "left" | "right";
        };
        block?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        checkAll?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        checked?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        defaultChecked?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        content?: {
            type: StringConstructor;
            value?: string;
        };
        contentDisabled?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        style?: {
            type: StringConstructor;
            value?: string;
        };
        disabled?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        externalClasses?: {
            type: ArrayConstructor;
            value?: ["t-class", "t-class-icon", "t-class-label", "t-class-content", "t-class-border"];
        };
        icon?: {
            type: null;
            value?: string[] | "circle" | "rectangle" | "line";
        };
        indeterminate?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        label?: {
            type: StringConstructor;
            value?: string;
        };
        maxContentRow?: {
            type: NumberConstructor;
            value?: number;
        };
        maxLabelRow?: {
            type: NumberConstructor;
            value?: number;
        };
        name?: {
            type: StringConstructor;
            value?: string;
        };
        readonly?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        value?: {
            type: null;
            value?: string | number | boolean;
        };
    };
    data: {
        prefix: string;
        classPrefix: string;
    };
    controlledProps: {
        key: string;
        event: string;
    }[];
    methods: {
        onChange(e: WechatMiniprogram.TouchEvent): void;
    };
}
