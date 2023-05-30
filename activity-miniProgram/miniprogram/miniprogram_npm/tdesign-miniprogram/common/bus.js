export default class Bus {
    constructor() {
        this.listeners = new Map();
    }
    on(evtName, listener) {
        const target = this.listeners.get(evtName) || [];
        target.push(listener);
        this.listeners.set(evtName, target);
    }
    emit(evtName) {
        const listeners = this.listeners.get(evtName);
        if (listeners) {
            listeners.forEach((func) => func());
        }
    }
}
