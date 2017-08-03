require connman.inc

# do not adjust dirs, only import them
VELIB_DEFAULT_DIRS = "1"
inherit ve_package

SRC_URI  = "https://www.kernel.org/pub/linux/network/connman/connman-${PV}.tar.xz \
            file://0001-Set-hwclock-when-time-is-decoded.patch \
            file://0002-Increase-NTP-poll-interval.patch \
            file://0003-gweb-Do-not-lookup-for-a-NULL-key-in-a-hash-table.patch \
            file://0004-wifi-make-max-connection-retries-configurable.patch \
            file://0001-service-Update-nameservers-and-timeservers-with-chan.patch \
            file://main.conf \
            file://connman \
            file://connmand-watch.sh \
           "

SRC_URI[md5sum] = "c51903fd3e7a6a371d12ac5d72a1fa01"
SRC_URI[sha256sum] = "bc8946036fa70124d663136f9f6b6238d897ca482782df907b07a428b09df5a0"

PR = "${INC_PR}.0"

do_configure_append() {
	sed -i -e 's:\$(localstatedir)/lib:${permanentlocalstatedir}/lib:' ${B}/Makefile
}

do_install_append() {
	install -d ${D}${sysconfdir}/connman
	install -m 0644 ${WORKDIR}/main.conf ${D}${sysconfdir}/connman/main.conf
}
