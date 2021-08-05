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
        "total": "221",
        "ok": "221",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "439",
        "ok": "439",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "338",
        "ok": "338",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "69",
        "ok": "69",
        "ko": "-"
    },
    "percentiles1": {
        "total": "347",
        "ok": "347",
        "ko": "-"
    },
    "percentiles2": {
        "total": "393",
        "ok": "393",
        "ko": "-"
    },
    "percentiles3": {
        "total": "424",
        "ok": "424",
        "ko": "-"
    },
    "percentiles4": {
        "total": "436",
        "ok": "436",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 10,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "10",
        "ok": "10",
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
        "total": "364",
        "ok": "364",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "439",
        "ok": "439",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "398",
        "ok": "398",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "25",
        "ok": "25",
        "ko": "-"
    },
    "percentiles1": {
        "total": "397",
        "ok": "397",
        "ko": "-"
    },
    "percentiles2": {
        "total": "406",
        "ok": "406",
        "ko": "-"
    },
    "percentiles3": {
        "total": "432",
        "ok": "432",
        "ko": "-"
    },
    "percentiles4": {
        "total": "438",
        "ok": "438",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 5,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5",
        "ok": "5",
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
        "total": "221",
        "ok": "221",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "329",
        "ok": "329",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "278",
        "ok": "278",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "41",
        "ok": "41",
        "ko": "-"
    },
    "percentiles1": {
        "total": "298",
        "ok": "298",
        "ko": "-"
    },
    "percentiles2": {
        "total": "301",
        "ok": "301",
        "ko": "-"
    },
    "percentiles3": {
        "total": "323",
        "ok": "323",
        "ko": "-"
    },
    "percentiles4": {
        "total": "328",
        "ok": "328",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 5,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5",
        "ok": "5",
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
