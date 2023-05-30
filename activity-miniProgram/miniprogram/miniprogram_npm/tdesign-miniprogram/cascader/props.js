const props = {
    closeBtn: {
        type: Boolean,
        value: true,
    },
    keys: {
        type: Object,
    },
    options: {
        type: Array,
        value: [],
    },
    subTitles: {
        type: Array,
        value: [],
    },
    theme: {
        type: String,
        value: 'step',
    },
    title: {
        type: String,
    },
    value: {
        type: null,
        value: null,
    },
    defaultValue: {
        type: null,
        value: null,
    },
    visible: {
        type: Boolean,
        value: false,
    },
};
export default props;
