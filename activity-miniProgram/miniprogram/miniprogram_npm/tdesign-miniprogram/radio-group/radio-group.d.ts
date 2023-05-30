import { SuperComponent, RelationsOptions } from '../common/src/index';
export default class RadioGroup extends SuperComponent {
    externalClasses: string[];
    data: {
        prefix: string;
        classPrefix: string;
        radioOptions: any[];
    };
    relations: RelationsOptions;
    properties: import("./type").TdRadioGroupProps<import("../radio/type").RadioValue>;
    controlledProps: {
        key: string;
        event: string;
    }[];
    observers: {
        value(v: any): void;
        options(): void;
    };
    methods: {
        getChilds(): any;
        updateValue(value: any): void;
        handleRadioChange(e: any): void;
        initWithOptions(): void;
    };
}
