import { SuperComponent } from '../common/src/index';
export default class Stepper extends SuperComponent {
    externalClasses: string[];
    options: {
        addGlobalClass: boolean;
    };
    properties: {
        style?: {
            type: StringConstructor;
            value?: string;
        };
        disableInput?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        disabled?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        externalClasses?: {
            type: ArrayConstructor;
            value?: ["t-class", "t-class-input", "t-class-add", "t-class-minus"];
        };
        inputWidth?: {
            type: NumberConstructor;
            value?: number;
        };
        max?: {
            type: NumberConstructor;
            value?: number;
        };
        min?: {
            type: NumberConstructor;
            value?: number;
        };
        step?: {
            type: NumberConstructor;
            value?: number;
        };
        size?: {
            type: StringConstructor;
            value?: string;
        };
        theme?: {
            type: StringConstructor;
            value?: "outline" | "normal" | "filled";
        };
        value?: {
            type: StringConstructor;
            optionalTypes: NumberConstructor[];
            value?: string | number;
        };
        defaultValue?: {
            type: StringConstructor;
            optionalTypes: NumberConstructor[];
            value?: string | number;
        };
    };
    controlledProps: {
        key: string;
        event: string;
    }[];
    observers: {
        value(v: any): void;
    };
    data: {
        currentValue: number;
        classPrefix: string;
        prefix: string;
    };
    attached(): void;
    isDisabled(type: any): boolean;
    format(value: any): number;
    setValue(value: any): void;
    minusValue(): boolean;
    plusValue(): boolean;
    changeValue(e: any): string | 0;
    focusHandle(e: any): void;
    inputHandle(e: any): void;
    blurHandle(e: any): void;
}
