/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.mrql;

import java.io.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.fs.*;


/** a wrapper of a DataSet (stored in HDFS) as an MRData */
final public class MR_dataset extends MRData {
    public DataSet dataset;

    public MR_dataset ( DataSet d ) { dataset = d; }

    public void materializeAll () {};

    public DataSet dataset () { return dataset; }

    public void write ( DataOutput out ) throws IOException {
        new Bag(dataset.take(Integer.MAX_VALUE)).write(out);
    }

    private void writeObject ( ObjectOutputStream out ) throws IOException {
        writeData(out);
    }

    public void writeData ( ObjectOutputStream out ) throws IOException {
        throw new Error("DataSets are not serializable");
    }

    public void readFields ( DataInput in ) throws IOException {
        throw new Error("DataSets are not serializable");
    }

    public int compareTo ( MRData x ) {
        throw new Error("DataSets cannot be compared");
    }

    public boolean equals ( Object x ) {
        throw new Error("DataSets cannot be compared");
    }
}
