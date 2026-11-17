'use strict';

// Angular framework packages have exact cross-package peer dependencies.
// Catch mismatched patches in dependency PRs before npm ci or the Docker build.
const fs = require('node:fs');
const path = require('node:path');
const lock = JSON.parse(fs.readFileSync(path.join(__dirname, '..', 'package-lock.json'), 'utf8'));

const frameworkPackages = [
  '@angular/common', '@angular/core', '@angular/compiler',
  '@angular/forms', '@angular/platform-browser',
  '@angular/platform-server', '@angular/router',
  '@angular/compiler-cli'
];
const versions = new Map();
for (const name of frameworkPackages) {
  const pkg = lock.packages['node_modules/' + name];
  if (!pkg || !pkg.version) {
    console.error('Angular lockfile validation failed: missing ' + name);
    process.exitCode = 1;
    continue;
  }
  versions.set(name, pkg.version);
}

const unique = new Set(versions.values());
if (unique.size !== 1 || versions.size !== frameworkPackages.length) {
  console.error('Angular lockfile patch versions must match:');
  for (const [name, version] of versions) console.error('  ' + name + ': ' + version);
  console.error('Update Angular framework dependencies together, regenerate package-lock.json with npm, and run npm ci.');
  process.exitCode = 1;
} else {
  console.log('Angular framework lockfile versions aligned at ' + [...unique][0]);
}
