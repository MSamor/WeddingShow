const props = {
    addContent: {
        type: String,
    },
    allowUploadDuplicateFile: {
        type: Boolean,
        value: false,
    },
    config: {
        type: Object,
    },
    files: {
        type: Array,
        value: null,
    },
    defaultFiles: {
        type: Array,
    },
    gridConfig: {
        type: Object,
    },
    gutter: {
        type: Number,
        value: 16,
    },
    imageProps: {
        type: Object,
    },
    max: {
        type: Number,
        value: 0,
    },
    mediaType: {
        type: Array,
        value: ['image', 'video'],
    },
    requestMethod: {
        type: null,
    },
    sizeLimit: {
        type: null,
    },
    source: {
        type: String,
        value: 'media',
    },
};
export default props;
