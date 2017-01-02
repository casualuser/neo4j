/*
 * Copyright (c) 2002-2017 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.server.rest;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DisableWADLIT extends AbstractRestFunctionalTestBase
{
    @Test
    public void should404OnAnyUriEndinginWADL() throws Exception
    {
        URI nodeUri = new URI( "http://localhost:7474/db/data/application.wadl" );

        HttpClient httpclient = new DefaultHttpClient();
        try
        {
            HttpGet httpget = new HttpGet( nodeUri );

            httpget.setHeader( "Accept", "*/*" );
            HttpResponse response = httpclient.execute( httpget );

            assertEquals( 404, response.getStatusLine().getStatusCode() );

        }
        finally
        {
            httpclient.getConnectionManager().shutdown();
        }
    }
}
