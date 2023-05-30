import { SuperComponent } from '../common/src/index';
export default class TreeSelect extends SuperComponent {
    externalClasses: string[];
    options: {
        multipleSlots: boolean;
    };
    data: {
        prefix: string;
        classPrefix: string;
        labelAlias: string;
        valueAlias: string;
    };
    properties: import("./type").TdTreeSelectProps<import("../common/common").TreeOptionData>;
    controlledProps: {
        key: string;
        event: string;
    }[];
    observers: {
        value(): void;
        keys(obj: any): void;
    };
    methods: {
        buildTreeOptions(): void;
        onRootChange(e: any): void;
        handleTreeClick(e: any): void;
        handleRadioChange(e: any): void;
    };
}
