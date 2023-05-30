const props = {
    content: {
        type: null,
    },
    direction: {
        type: String,
        value: 'horizontal',
    },
    externalClasses: {
        type: Array,
    },
    operation: {
        type: String,
    },
    marquee: {
        type: null,
        value: false,
    },
    prefixIcon: {
        type: null,
        value: true,
    },
    suffixIcon: {
        type: null,
        value: null,
    },
    theme: {
        type: String,
        value: 'info',
    },
    visible: {
        type: Boolean,
        value: null,
    },
    defaultVisible: {
        type: Boolean,
        value: false,
    },
};
export default props;
