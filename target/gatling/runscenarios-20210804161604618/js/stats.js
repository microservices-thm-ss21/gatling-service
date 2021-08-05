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
        "total": "304",
        "ok": "304",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "478",
        "ok": "478",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "376",
        "ok": "376",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "64",
        "ok": "64",
        "ko": "-"
    },
    "percentiles1": {
        "total": "341",
        "ok": "341",
        "ko": "-"
    },
    "percentiles2": {
        "total": "428",
        "ok": "428",
        "ko": "-"
    },
    "percentiles3": {
        "total": "478",
        "ok": "478",
        "ko": "-"
    },
    "percentiles4": {
        "total": "478",
        "ok": "478",
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
        "total": "304",
        "ok": "304",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "346",
        "ok": "346",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "325",
        "ok": "325",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "15",
        "ok": "15",
        "ko": "-"
    },
    "percentiles1": {
        "total": "320",
        "ok": "320",
        "ko": "-"
    },
    "percentiles2": {
        "total": "336",
        "ok": "336",
        "ko": "-"
    },
    "percentiles3": {
        "total": "344",
        "ok": "344",
        "ko": "-"
    },
    "percentiles4": {
        "total": "346",
        "ok": "346",
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
        "total": "335",
        "ok": "335",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "478",
        "ok": "478",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "427",
        "ok": "427",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "53",
        "ok": "53",
        "ko": "-"
    },
    "percentiles1": {
        "total": "435",
        "ok": "435",
        "ko": "-"
    },
    "percentiles2": {
        "total": "478",
        "ok": "478",
        "ko": "-"
    },
    "percentiles3": {
        "total": "478",
        "ok": "478",
        "ko": "-"
    },
    "percentiles4": {
        "total": "478",
        "ok": "478",
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
