export interface TdCheckboxGroupProps {
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
        value?: Array<CheckboxOption>;
    };
    value?: {
        type: ArrayConstructor;
        value?: CheckboxGroupValue;
    };
    defaultValue?: {
        type: ArrayConstructor;
        value?: CheckboxGroupValue;
    };
}
export declare type CheckboxOption = string | number | CheckboxOptionObj;
export interface CheckboxOptionObj {
    label?: string;
    value?: string | number;
    disabled?: boolean;
    checkAll?: true;
}
export declare type CheckboxGroupValue = Array<string | number>;
