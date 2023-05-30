const props = {
    action: {
        type: String,
        value: '',
    },
    center: {
        type: Boolean,
        value: false,
    },
    disabled: {
        type: Boolean,
        value: false,
    },
    externalClasses: {
        type: Array,
    },
    focus: {
        type: Boolean,
        value: false,
    },
    label: {
        type: String,
        value: '',
    },
    leftIcon: {
        type: String,
        value: 'search',
    },
    placeholder: {
        type: String,
        value: '',
    },
    rightIcon: {
        type: String,
        value: 'close-circle-filled',
    },
    shape: {
        type: String,
        value: 'square',
    },
    value: {
        type: String,
        value: '',
    },
    clearable: {
        type: Boolean,
        value: true,
    },
    type: {
        type: String,
        value: 'text',
    },
};
export default props;
