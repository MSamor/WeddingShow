export default class Bus {
    listeners: Map<string, any>;
    constructor();
    on(evtName: string, listener: any): void;
    emit(evtName: string): void;
}
