import { SuperComponent, RelationsOptions } from '../common/src/index';
import type { TdCollapsePanelProps } from './type';
export interface CollapsePanelProps extends TdCollapsePanelProps {
}
export default class CollapsePanel extends SuperComponent {
    externalClasses: string[];
    options: {
        multipleSlots: boolean;
    };
    relations: RelationsOptions;
    properties: TdCollapsePanelProps;
    data: {
        prefix: string;
        expanded: boolean;
        classPrefix: string;
        classBasePrefix: string;
        ultimateExpandIcon: boolean;
        ultimateDisabled: boolean;
    };
    methods: {
        set(data: Record<string, object | any>): Promise<unknown>;
        updateExpanded(activeValues: any): void;
        updateStyle(expanded: boolean): Promise<void>;
        onClick(): void;
    };
}
