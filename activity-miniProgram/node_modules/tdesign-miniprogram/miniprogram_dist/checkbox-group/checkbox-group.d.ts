import { SuperComponent, RelationsOptions } from '../common/src/index';
export default class CheckBoxGroup extends SuperComponent {
    externalClasses: string[];
    relations: RelationsOptions;
    data: {
        prefix: string;
        classPrefix: string;
        checkboxOptions: any[];
    };
    properties: {
        borderless: {
            type: BooleanConstructor;
            value: boolean;
        };
        style?: {
            type: StringConstructor;
            value?: string;
        };
        disabled?: {
            type: BooleanConstructor;
            value?: boolean;
        };
        max?: {
            type: NumberConstructor;
            value?: number;
        };
        name?: {
            type: StringConstructor;
            value?: string;
        };
        options?: {
            type: ArrayConstructor;
            value?: import("./type").CheckboxOption[];
        };
        value?: {
            type: ArrayConstructor;
            value?: import("./type").CheckboxGroupValue;
        };
        defaultValue?: {
            type: ArrayConstructor;
            value?: import("./type").CheckboxGroupValue;
        };
    };
    observers: {
        value(): void;
        options(): void;
    };
    lifetimes: {
        ready(): void;
    };
    controlledProps: {
        key: string;
        event: string;
    }[];
    $checkAll: any;
    methods: {
        getChilds(): any;
        updateChildren(): void;
        updateValue({ value, checked, checkAll, indeterminate }: {
            value: any;
            checked: any;
            checkAll: any;
            indeterminate: any;
        }): void;
        initWithOptions(): void;
        handleInnerChildChange(e: any): void;
        setCheckall(): void;
    };
}
