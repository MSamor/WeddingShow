export default {
    background: {
        color: {
            // value: "#f1ab37",
            value: "#fbcdc6",
        },
    },
    particles: {
        color: {
            value: [
                "#FFAEBC",
                "#A0E7E5",
                "#B4F8C8",
                "#FBE7C6",
                "#FFC9AE",
                "#FFAEE5",
                "#A0C6E7",
                "#A0E7C2",
                "#B4F8EA",
                "#C2F8B4",
                "#F4FBC6",
                "#FBCDC6",
            ],
        },
        move: {
            angle: {
                offset: 0,
                value: 15,
            },
            direction: "bottom",
            enable: true,
            outModes: {
                default: "out",
            },
            speed: 3,
        },
        number: {
            value: 300,
        },
        opacity: {
            value: 1,
        },
        shape: {
            type: "star",
        },
        size: {
            value: 16,
        },
        roll: {
            darken: {
                enable: true,
                value: 30,
            },
            enlighten: {
                enable: true,
                value: 30,
            },
            enable: true,
            mode: "horizontal",
            speed: {
                min: 5,
                max: 15,
            },
        },
        zIndex: {
            value: {
                min: 0,
                max: 100,
            },
            opacityRate: 0,
            velocityRate: 2,
        },
    },
}