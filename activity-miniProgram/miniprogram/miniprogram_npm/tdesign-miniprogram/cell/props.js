const props = {
    align: {
        type: String,
        value: 'middle',
    },
    arrow: {
        type: Boolean,
        value: false,
    },
    bordered: {
        type: Boolean,
        value: true,
    },
    description: {
        type: String,
    },
    externalClasses: {
        type: Array,
    },
    hover: {
        type: Boolean,
    },
    image: {
        type: String,
    },
    jumpType: {
        type: String,
        value: 'navigateTo',
    },
    leftIcon: {
        type: String,
    },
    note: {
        type: String,
    },
    required: {
        type: Boolean,
        value: false,
    },
    rightIcon: {
        type: String,
    },
    title: {
        type: String,
    },
    url: {
        type: String,
        value: '',
    },
};
export default props;
