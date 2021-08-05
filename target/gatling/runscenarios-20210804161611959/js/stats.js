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
        "total": "238",
        "ok": "238",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "378",
        "ok": "378",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "323",
        "ok": "323",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "41",
        "ok": "41",
        "ko": "-"
    },
    "percentiles1": {
        "total": "319",
        "ok": "319",
        "ko": "-"
    },
    "percentiles2": {
        "total": "360",
        "ok": "360",
        "ko": "-"
    },
    "percentiles3": {
        "total": "374",
        "ok": "374",
        "ko": "-"
    },
    "percentiles4": {
        "total": "377",
        "ok": "377",
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
        "total": "331",
        "ok": "331",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "378",
        "ok": "378",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "358",
        "ok": "358",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "17",
        "ok": "17",
        "ko": "-"
    },
    "percentiles1": {
        "total": "365",
        "ok": "365",
        "ko": "-"
    },
    "percentiles2": {
        "total": "369",
        "ok": "369",
        "ko": "-"
    },
    "percentiles3": {
        "total": "376",
        "ok": "376",
        "ko": "-"
    },
    "percentiles4": {
        "total": "378",
        "ok": "378",
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
        "total": "238",
        "ok": "238",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "306",
        "ok": "306",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "289",
        "ok": "289",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "26",
        "ok": "26",
        "ko": "-"
    },
    "percentiles1": {
        "total": "301",
        "ok": "301",
        "ko": "-"
    },
    "percentiles2": {
        "total": "304",
        "ok": "304",
        "ko": "-"
    },
    "percentiles3": {
        "total": "306",
        "ok": "306",
        "ko": "-"
    },
    "percentiles4": {
        "total": "306",
        "ok": "306",
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
