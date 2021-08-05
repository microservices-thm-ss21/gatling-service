var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "2",
        "ok": "0",
        "ko": "2"
    },
    "minResponseTime": {
        "total": "236",
        "ok": "-",
        "ko": "236"
    },
    "maxResponseTime": {
        "total": "1517",
        "ok": "-",
        "ko": "1517"
    },
    "meanResponseTime": {
        "total": "877",
        "ok": "-",
        "ko": "877"
    },
    "standardDeviation": {
        "total": "641",
        "ok": "-",
        "ko": "641"
    },
    "percentiles1": {
        "total": "877",
        "ok": "-",
        "ko": "877"
    },
    "percentiles2": {
        "total": "1197",
        "ok": "-",
        "ko": "1197"
    },
    "percentiles3": {
        "total": "1453",
        "ok": "-",
        "ko": "1453"
    },
    "percentiles4": {
        "total": "1504",
        "ok": "-",
        "ko": "1504"
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
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 2,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "1",
        "ok": "-",
        "ko": "1"
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
        "total": "1",
        "ok": "0",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "236",
        "ok": "-",
        "ko": "236"
    },
    "maxResponseTime": {
        "total": "236",
        "ok": "-",
        "ko": "236"
    },
    "meanResponseTime": {
        "total": "236",
        "ok": "-",
        "ko": "236"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "-",
        "ko": "0"
    },
    "percentiles1": {
        "total": "236",
        "ok": "-",
        "ko": "236"
    },
    "percentiles2": {
        "total": "236",
        "ok": "-",
        "ko": "236"
    },
    "percentiles3": {
        "total": "236",
        "ok": "-",
        "ko": "236"
    },
    "percentiles4": {
        "total": "236",
        "ok": "-",
        "ko": "236"
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
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 1,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.5",
        "ok": "-",
        "ko": "0.5"
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
        "total": "1",
        "ok": "0",
        "ko": "1"
    },
    "minResponseTime": {
        "total": "1517",
        "ok": "-",
        "ko": "1517"
    },
    "maxResponseTime": {
        "total": "1517",
        "ok": "-",
        "ko": "1517"
    },
    "meanResponseTime": {
        "total": "1517",
        "ok": "-",
        "ko": "1517"
    },
    "standardDeviation": {
        "total": "0",
        "ok": "-",
        "ko": "0"
    },
    "percentiles1": {
        "total": "1517",
        "ok": "-",
        "ko": "1517"
    },
    "percentiles2": {
        "total": "1517",
        "ok": "-",
        "ko": "1517"
    },
    "percentiles3": {
        "total": "1517",
        "ok": "-",
        "ko": "1517"
    },
    "percentiles4": {
        "total": "1517",
        "ok": "-",
        "ko": "1517"
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
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 1,
    "percentage": 100
},
    "meanNumberOfRequestsPerSecond": {
        "total": "0.5",
        "ok": "-",
        "ko": "0.5"
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
