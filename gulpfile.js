var gulp = require('gulp'),
    cssSourceGlob = './postcss/**/*.css';

gulp.task('default', function() {
    gulp.start('postcss');
    gulp.start('watch');
});

gulp.task('postcss', function() {
    var sourcemaps = require('gulp-sourcemaps'),
        postcss = require('gulp-postcss'),
        nested = require ('postcss-nested'),
        simpleVars = require('postcss-simple-vars'),
        autoprefixer = require('autoprefixer');    
    return gulp.src(cssSourceGlob)
        .pipe(sourcemaps.init())
        .pipe(postcss([nested, simpleVars, autoprefixer]))
        .pipe(sourcemaps.write('.'))
        .pipe(gulp.dest('./resources/public/css/'));
});

gulp.task('watch', function() {
    gulp.watch(cssSourceGlob, ['postcss']);
})
