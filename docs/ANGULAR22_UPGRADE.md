# Coordinated Angular 22 upgrade

This branch moves all core Angular packages, CLI, build, SSR and compiler tooling from Angular 21 to compatible Angular 22.2.x releases together. TypeScript moves to 6.0.x. Version ranges use semver; the generated lockfile is the actual reproducibility source.

A dedicated, branch-only CI workflow regenerates the lockfile on GitHub's Ubuntu 24.04 Node 22 runner, **runs npm ci and a production build before committing**, and keeps the GitHub token out of npm install/build steps. The normal full-stack workflow must pass separately on the final lockfile.

Do not merge the old single-package Angular Dependabot PRs (#24, #25, #37–39) on top of this upgrade. If the branch-only check fails, review and fix source compatibility before using this version. Do not assume passing frontend builds prove a live browser deployment.
