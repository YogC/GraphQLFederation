/*
 * Copyright 2020 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo.services

import com.example.demo.generated.types.Claim
import com.example.demo.generated.types.Show
import org.springframework.stereotype.Service

interface ClaimsService {
    fun retrieveClaim(vararg id: String): List<Claim>
    fun retrieveClaims(): List<Claim>
}

@Service
class BasicClaimsService : ClaimsService {

     override fun retrieveClaims(): List<Claim> {

        print("Coming here retrieveClaims...................")
        return listOf(
                Claim("1","2B784921", "A", "IL"),

                Claim("2","2B784922", "F", "IL"),

        )
    }

    override fun retrieveClaim( vararg id: String ): List<Claim> {
        print("Coming here...................$id")
        print( id.forEach { println(it) })
        return   listOf(
                Claim("1","2B784921", "A", "IL"),
                Claim("2","2B784922", "F", "IL"),
                ).filter { claim -> claim.claimId == "1"};
     }
}