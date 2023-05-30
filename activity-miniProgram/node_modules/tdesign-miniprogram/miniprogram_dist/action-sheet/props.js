const props = {
    align: {
        type: String,
        value: 'center',
    },
    cancelText: {
        type: String,
        value: '取消',
    },
    count: {
        type: Number,
        value: 8,
    },
    description: {
        type: String,
        value: '',
    },
    items: {
        type: Array,
    },
    showCancel: {
        type: Boolean,
        value: true,
    },
    theme: {
        type: String,
        value: 'list',
    },
    visible: {
        type: Boolean,
        value: null,
    },
    defaultVisible: {
        type: Boolean,
        value: false,
    },
    popupProps: {
        type: Object,
        value: {},
    },
};
export default props;
