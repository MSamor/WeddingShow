export interface TdSearchProps {
    style?: {
        type: StringConstructor;
        value?: string;
    };
    action?: {
        type: StringConstructor;
        value?: string;
    };
    center?: {
        type: BooleanConstructor;
        value?: boolean;
    };
    disabled?: {
        type: BooleanConstructor;
        value?: boolean;
    };
    externalClasses?: {
        type: ArrayConstructor;
        value?: ['t-class', 't-class-input', 't-class-input-container', 't-class-cancel', 't-class-left', 't-class-right'];
    };
    focus?: {
        type: BooleanConstructor;
        value?: boolean;
    };
    label?: {
        type: StringConstructor;
        value?: string;
    };
    leftIcon?: {
        type: StringConstructor;
        value?: string;
    };
    placeholder?: {
        type: StringConstructor;
        value?: string;
    };
    rightIcon?: {
        type: StringConstructor;
        value?: string;
    };
    shape?: {
        type: StringConstructor;
        value?: 'square' | 'round';
    };
    value?: {
        type: StringConstructor;
        value?: string;
    };
    clearable: {
        type: BooleanConstructor;
        value?: boolean;
    };
    type: {
        type: StringConstructor;
        value?: string;
    };
}
