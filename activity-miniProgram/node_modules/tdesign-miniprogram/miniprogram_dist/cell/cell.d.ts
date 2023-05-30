import { SuperComponent, RelationsOptions } from '../common/src/index';
export default class Cell extends SuperComponent {
    externalClasses: string[];
    relations: RelationsOptions;
    options: {
        multipleSlots: boolean;
    };
    properties: import("./type").TdCellProps;
    data: {
        prefix: string;
        classPrefix: string;
        isLastChild: boolean;
    };
    onClick(e: any): void;
    jumpLink(urlKey?: string, link?: string): void;
}
