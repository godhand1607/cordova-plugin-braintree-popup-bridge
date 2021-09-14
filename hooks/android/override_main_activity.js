#!/usr/bin/env node

var fs = require('fs'),
    path = require('path'),
    xml2js = require('xml2js'),
    q = require('q'),
    parser = new xml2js.Parser();

module.exports = function (context) {
    var deferral = q.defer();


    var PACKAGE_NAME_PLACEHOLDER = "<%PACKAGE_NAME%>";

    var packageName, activityTargetPath;

    var projectRoot = context.opts.projectRoot;
    var platformRoot = path.join(projectRoot, 'platforms/android');

    var activitySourcePath = path.join(context.opts.plugin.pluginInfo.dir, 'src/android/MainActivity.java');
    // console.log("activitySourcePath: "+activitySourcePath);

    var configXmlPath =  path.join(projectRoot, 'config.xml');

    fs.readFile(configXmlPath, function(err, data) {
        if(err){
            deferral.reject("Failed to read config.xml: " + err);
        }
        parser.parseString(data, function (err, result) {
            if(err){
                deferral.reject("Failed to parse config.xml: " + err);
            }
            // console.log("result: " + JSON.stringify(result));

            packageName = result.widget.$['android-packageName'];
            activityTargetPath = path.join(platformRoot, 'app/src/main/java', packageName.replace(/\./g,'/'), 'MainActivity.java');
            // console.log("activityTargetPath: "+activityTargetPath);

            fs.readFile(activitySourcePath, function(err, activitySrc) {
                if (err) {
                    deferral.reject("Failed to read source MainActivity.java: " + err)
                }
                activitySrc = activitySrc.toString().replace(PACKAGE_NAME_PLACEHOLDER, packageName);
                fs.writeFile(activityTargetPath, activitySrc, 'utf8', function (err) {
                    if (err) {
                        deferral.reject("Failed to write target MainActivity.java: " + err);
                    }
                    deferral.resolve();
                    //console.log("SUCCESS!");
                });
            });

        });
    });
    return deferral.promise;
};
