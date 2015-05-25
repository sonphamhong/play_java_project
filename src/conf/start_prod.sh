#!/bin/bash

nohup bin/zombie2 -J-server -J-Xms128M -J-Xmx512m -Dconfig.resource=application-prod.conf -Dhttp.port=9000 -Dhttp.address=127.0.0.1 &
