# npm patch dependency maintenance

Regenerate the frontend package lockfile within the existing Angular 22 and other semver constraints, then verify it with `npm ci` and a production Angular build. This helps resolve older transitive dependency vulnerabilities without importing incompatible stale Dependabot lockfiles.

The one-time GitHub Actions lockfile generator is **removed before merge**. Normal Java backend, Angular and Docker smoke CI must pass on the final pull-request head.

This change is not a claim that an npm audit has no advisories, or that all old Dependabot PRs have been fixed. Review resolved installed versions and close only updates already included or no longer applicable.
