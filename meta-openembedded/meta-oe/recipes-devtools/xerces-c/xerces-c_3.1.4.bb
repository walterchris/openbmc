SUMMARY = "Xerces-c is a validating xml parser written in C++"
DESCRIPTION = "Xerces-C++ makes it easy to give your application \
               the ability to read and write XML data. \
               A shared library is provided for parsing, generating, \
               manipulating, and validating XML documents using \
               the DOM, SAX, and SAX2 APIs."
HOMEPAGE = "http://xerces.apache.org/xerces-c/"
SECTION =  "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "http://archive.apache.org/dist/xerces/c/3/sources/${BP}.tar.bz2"
SRC_URI[md5sum] = "d04ae9d8b2dee2157c6db95fa908abfd"
SRC_URI[sha256sum] = "9408f12c1628ecf80730bedbe8b2caad810edd01bb4c66f77b60c873e8cc6891"

inherit autotools

PACKAGECONFIG ??= "curl icu"
PACKAGECONFIG[curl] = "--with-curl=${STAGING_DIR_TARGET}${prefix},--with-curl=no,curl"
PACKAGECONFIG[icu] = "--with-icu=${STAGING_DIR_TARGET}${prefix},--with-icu=no,icu"

do_install:prepend () {
    sed -i -e 's:-L${STAGING_DIR}/lib:-L\$\{libdir\}:g' ${B}/xerces-c.pc
}

PACKAGES = "libxerces-c \
    libxerces-c-dev \
    xerces-c-samples \
    libxerces-c-staticdev \
    ${PN}-dbg \
"

RPROVIDES:${PN}-dbg += "libxerces-c-dbg xerces-c-samples-dbg"

FILES:libxerces-c = "${libdir}/libxerces-c-3.1.so"
FILES:libxerces-c-dev = "${libdir}/lib*.la \
    ${libdir}/libxerces-c.so \
    ${libdir}/pkgconfig/xerces-c.pc \
    ${includedir}/xercesc \
"
FILES:xerces-c-samples = "${bindir}/*"
FILES:libxerces-c-staticdev = "${libdir}/lib*.a"

BBCLASSEXTEND = "native"
