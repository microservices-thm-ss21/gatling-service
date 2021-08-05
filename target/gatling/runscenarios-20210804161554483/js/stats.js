var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "10",
        "ok": "10",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2406",
        "ok": "2406",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3020",
        "ok": "3020",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "2847",
        "ok": "2847",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "214",
        "ok": "214",
        "ko": "-"
    },
    "percentiles1": {
        "total": "2990",
        "ok": "2990",
        "ko": "-"
    },
    "percentiles2": {
        "total": "2991",
        "ok": "2991",
        "ko": "-"
    },
    "percentiles3": {
        "total": "3007",
        "ok": "3007",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3017",
        "ok": "3017",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 10,
    "percentage": 100
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1.429",
        "ok": "1.429",
        "ko": "-"
    }
},
contents: {
"req_loginaspeterhtt-aa318": {
        type: "REQUEST",
        name: "loginAsPeterHTTP",
path: "loginAsPeterHTTP",
pathFormatted: "req_loginaspeterhtt-aa318",
stats: {
    "name": "loginAsPeterHTTP",
    "numberOfRequests": {
        "total": "5",
        "ok": "5",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2989",
        "ok": "2989",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "2992",
        "ok": "2992",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "2990",
        "ok": "2990",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "1",
        "ok": "1",
        "ko": "-"
    },
    "percentiles1": {
        "total": "2990",
        "ok": "2990",
        "ko": "-"
    },
    "percentiles2": {
        "total": "2991",
        "ok": "2991",
        "ko": "-"
    },
    "percentiles3": {
        "total": "2992",
        "ok": "2992",
        "ko": "-"
    },
    "percentiles4": {
        "total": "2992",
        "ok": "2992",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 5,
    "percentage": 100
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.714",
        "ok": "0.714",
        "ko": "-"
    }
}
    },"req_createprojectht-3ac66": {
        type: "REQUEST",
        name: "createProjectHTTP",
path: "createProjectHTTP",
pathFormatted: "req_createprojectht-3ac66",
stats: {
    "name": "createProjectHTTP",
    "numberOfRequests": {
        "total": "5",
        "ok": "5",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "2406",
        "ok": "2406",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "3020",
        "ok": "3020",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "2704",
        "ok": "2704",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "225",
        "ok": "225",
        "ko": "-"
    },
    "percentiles1": {
        "total": "2796",
        "ok": "2796",
        "ko": "-"
    },
    "percentiles2": {
        "total": "2807",
        "ok": "2807",
        "ko": "-"
    },
    "percentiles3": {
        "total": "2977",
        "ok": "2977",
        "ko": "-"
    },
    "percentiles4": {
        "total": "3011",
        "ok": "3011",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 0,
    "percentage": 0
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 5,
    "percentage": 100
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.714",
        "ok": "0.714",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
