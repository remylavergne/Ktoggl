package dev.remylavergne.ktoggl.report

import dev.remylavergne.ktoggl.report.models.BaseTime
import dev.remylavergne.ktoggl.report.models.WeeklyProjectsTime
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class DecodingStringTest {

    val projectsTimeJson = """
        {
            "total_grand": 141602000,
            "total_billable": null,
            "total_currencies": [
                {
                    "currency": null,
                    "amount": null
                }
            ],
            "data": [
                {
                    "title": {
                        "client": null,
                        "project": "Afelio Global",
                        "color": "0",
                        "hex_color": "#566614"
                    },
                    "pid": 167618992,
                    "totals": [
                        5852000,
                        6972000,
                        7165000,
                        null,
                        null,
                        null,
                        null,
                        19989000
                    ],
                    "details": [
                        {
                            "uid": 6485353,
                            "title": {
                                "user": "Rémy LAVERGNE"
                            },
                            "totals": [
                                5852000,
                                6972000,
                                7165000,
                                null,
                                null,
                                null,
                                null,
                                19989000
                            ]
                        }
                    ]
                },
                {
                    "title": {
                        "client": null,
                        "project": "BCEDWI - SPW",
                        "color": "0",
                        "hex_color": "#d94182"
                    },
                    "pid": 168651662,
                    "totals": [
                        4846000,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        4846000
                    ],
                    "details": [
                        {
                            "uid": 6485353,
                            "title": {
                                "user": "Rémy LAVERGNE"
                            },
                            "totals": [
                                4846000,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                4846000
                            ]
                        }
                    ]
                },
                {
                    "title": {
                        "client": null,
                        "project": "Ktoggl",
                        "color": "0",
                        "hex_color": "#0b83d9"
                    },
                    "pid": 168769451,
                    "totals": [
                        null,
                        null,
                        1088000,
                        7429000,
                        14931000,
                        13203000,
                        null,
                        36651000
                    ],
                    "details": [
                        {
                            "uid": 6485353,
                            "title": {
                                "user": "Rémy LAVERGNE"
                            },
                            "totals": [
                                null,
                                null,
                                1088000,
                                7429000,
                                14931000,
                                13203000,
                                null,
                                36651000
                            ]
                        }
                    ]
                },
                {
                    "title": {
                        "client": null,
                        "project": "MyMobility Ethias",
                        "color": "0",
                        "hex_color": "#c7af14"
                    },
                    "pid": 167246993,
                    "totals": [
                        null,
                        null,
                        null,
                        13777000,
                        null,
                        null,
                        null,
                        13777000
                    ],
                    "details": [
                        {
                            "uid": 6485353,
                            "title": {
                                "user": "Rémy LAVERGNE"
                            },
                            "totals": [
                                null,
                                null,
                                null,
                                13777000,
                                null,
                                null,
                                null,
                                13777000
                            ]
                        }
                    ]
                },
                {
                    "title": {
                        "client": null,
                        "project": "PRI PROD",
                        "color": "0",
                        "hex_color": "#c7af14"
                    },
                    "pid": 168714719,
                    "totals": [
                        null,
                        null,
                        6537000,
                        7201000,
                        null,
                        null,
                        null,
                        13738000
                    ],
                    "details": [
                        {
                            "uid": 6485353,
                            "title": {
                                "user": "Rémy LAVERGNE"
                            },
                            "totals": [
                                null,
                                null,
                                6537000,
                                7201000,
                                null,
                                null,
                                null,
                                13738000
                            ]
                        }
                    ]
                },
                {
                    "title": {
                        "client": null,
                        "project": "PRI V2",
                        "color": "0",
                        "hex_color": "#9e5bd9"
                    },
                    "pid": 167127880,
                    "totals": [
                        925000,
                        721000,
                        1238000,
                        null,
                        null,
                        null,
                        null,
                        2884000
                    ],
                    "details": [
                        {
                            "uid": 6485353,
                            "title": {
                                "user": "Rémy LAVERGNE"
                            },
                            "totals": [
                                925000,
                                721000,
                                1238000,
                                null,
                                null,
                                null,
                                null,
                                2884000
                            ]
                        }
                    ]
                },
                {
                    "title": {
                        "client": null,
                        "project": "toggl-sap-timesheet",
                        "color": "0",
                        "hex_color": "#d92b2b"
                    },
                    "pid": 168720674,
                    "totals": [
                        null,
                        null,
                        6356000,
                        null,
                        null,
                        null,
                        null,
                        6356000
                    ],
                    "details": [
                        {
                            "uid": 6485353,
                            "title": {
                                "user": "Rémy LAVERGNE"
                            },
                            "totals": [
                                null,
                                null,
                                6356000,
                                null,
                                null,
                                null,
                                null,
                                6356000
                            ]
                        }
                    ]
                },
                {
                    "title": {
                        "client": null,
                        "project": "Total Multi sites",
                        "color": "0",
                        "hex_color": "#d94182"
                    },
                    "pid": 168558950,
                    "totals": [
                        18197000,
                        19510000,
                        5654000,
                        null,
                        null,
                        null,
                        null,
                        43361000
                    ],
                    "details": [
                        {
                            "uid": 6485353,
                            "title": {
                                "user": "Rémy LAVERGNE"
                            },
                            "totals": [
                                18197000,
                                19510000,
                                5654000,
                                null,
                                null,
                                null,
                                null,
                                43361000
                            ]
                        }
                    ]
                }
            ],
            "week_totals": [
                29820000,
                27203000,
                28038000,
                28407000,
                14931000,
                13203000,
                null,
                141602000
            ]
        }
    """.trimIndent()

    val decodeFromString = Json.decodeFromString<BaseTime<List<WeeklyProjectsTime>>>(projectsTimeJson)

}