export interface TdFabProps {
    buttonProps?: {
        type: ObjectConstructor;
        value?: object;
    };
    style?: {
        type: StringConstructor;
        value?: string;
    };
    icon?: {
        type: StringConstructor;
        value?: string;
    };
    text?: {
        type: StringConstructor;
        value?: string;
    };
}
