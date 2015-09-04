var gulp = require('gulp'),
    cssSourceGlob = './postcss/main.css',
    fs = require('fs');

gulp.task('default', function() {
    gulp.start('postcss');
    gulp.start('watch');
});

gulp.task('postcss', function() {
    var sourcemaps = require('gulp-sourcemaps'),
        postcss = require('gulp-postcss'),
        nested = require ('postcss-nested'),
        simpleVars = require('postcss-simple-vars'),
        atImport = require('postcss-import')(),
        autoprefixer = require('autoprefixer');    
    return gulp.src(cssSourceGlob)
        .pipe(sourcemaps.init())
        .pipe(postcss([atImport, nested, simpleVars, autoprefixer]))
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest('./resources/public/css/'));
});

gulp.task('watch', function() {
    gulp.watch(cssSourceGlob, ['postcss']);
})
