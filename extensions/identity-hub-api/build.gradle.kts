/*
 *  Copyright (c) 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *
 */

plugins {
    `java-library`
    id("io.swagger.core.v3.swagger-gradle-plugin")
    `maven-publish`
}

val edcVersion: String by project
val edcGroup: String by project
val jupiterVersion: String by project
val restAssured: String by project
val mockitoVersion: String by project
val assertj: String by project
val nimbusVersion: String by project

dependencies {
    api(project(":spi:identity-hub-spi"))
    implementation(project(":spi:identity-hub-store-spi"))
    implementation(project(":core:identity-hub"))
    implementation("${edcGroup}:http:${edcVersion}")
    implementation("${edcGroup}:transaction-spi:${edcVersion}")


    testImplementation("${edcGroup}:junit:${edcVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${jupiterVersion}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${jupiterVersion}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${jupiterVersion}")

    testImplementation("com.nimbusds:nimbus-jose-jwt:${nimbusVersion}")
    testImplementation("org.assertj:assertj-core:${assertj}")
    testImplementation("io.rest-assured:rest-assured:${restAssured}")
    testImplementation("org.mockito:mockito-core:${mockitoVersion}")
    testImplementation(project(":spi:identity-hub-spi"))
    testImplementation(testFixtures(project(":spi:identity-hub-spi")))
    testImplementation(testFixtures(project(":spi:identity-hub-store-spi")))
}


publishing {
    publications {
        create<MavenPublication>("identity-hub-api") {
            artifactId = "identity-hub-api"
            from(components["java"])
        }
    }
}

