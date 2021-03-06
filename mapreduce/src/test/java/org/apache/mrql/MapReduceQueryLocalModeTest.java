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

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.GenericOptionsParser;

public class MapReduceQueryLocalModeTest extends QueryTest {
	public void tearDown() throws IOException {
		super.tearDown();
		Plan.clean();
	}

	@Override
	protected Evaluator createEvaluator() throws Exception {
		Configuration conf = new Configuration();

		Config.bsp_mode = false;
		Config.spark_mode = false;
		Config.map_reduce_mode = true;

		Evaluator.evaluator = new MapReduceEvaluator();

		Config.quiet_execution = true;

		String[] args = new String[] { "-local" };

		conf = Evaluator.evaluator.new_configuration();
		GenericOptionsParser gop = new GenericOptionsParser(conf, args);
		conf = gop.getConfiguration();

		args = gop.getRemainingArgs();

		Config.hadoop_mode = true;
		Config.testing = true;
		Config.parse_args(args, conf);
		
		Evaluator.evaluator.init(conf);
		
		return Evaluator.evaluator;
	}
}
