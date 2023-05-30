export interface TdLoadingProps {
    delay?: {
        type: NumberConstructor;
        value?: number;
    };
    duration?: {
        type: NumberConstructor;
        value?: number;
    };
    externalClasses?: {
        type: ArrayConstructor;
        value?: ['t-class', 't-class-text', 't-class-indicator'];
    };
    indicator?: {
        type: BooleanConstructor;
        value?: boolean;
    };
    inheritColor?: {
        type: BooleanConstructor;
        value?: boolean;
    };
    layout?: {
        type: StringConstructor;
        value?: 'horizontal' | 'vertical';
    };
    loading?: {
        type: BooleanConstructor;
        value?: boolean;
    };
    pause?: {
        type: BooleanConstructor;
        value?: boolean;
    };
    progress?: {
        type: NumberConstructor;
        value?: number;
    };
    reverse?: {
        type: BooleanConstructor;
        value?: boolean;
    };
    size?: {
        type: StringConstructor;
        value?: string;
    };
    text?: {
        type: StringConstructor;
        value?: string;
    };
    theme?: {
        type: StringConstructor;
        value?: 'circular' | 'spinner' | 'dots';
    };
}
