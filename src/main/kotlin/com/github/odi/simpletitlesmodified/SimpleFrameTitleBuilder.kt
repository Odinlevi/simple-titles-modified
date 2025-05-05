package com.github.odi.simpletitlesmodified

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.wm.impl.PlatformFrameTitleBuilder

class SimpleFrameTitleBuilder : PlatformFrameTitleBuilder() {
    private val state = TitleSettingsState.getInstance()

    override fun getProjectTitle(project: Project): String {
        var title = state.projectFormat
        if ("{DEFAULT}" in title) {
            title = title.replace("{DEFAULT}", super.getProjectTitle(project))
        }
        if ("{SIMPLE}" in title) {
            title = title.replace("{SIMPLE}", project.name)
        }
        return title
    }

    override suspend fun getFileTitleAsync(project: Project, file: VirtualFile): String {
        val defaultTitle = super.getFileTitleAsync(project, file)

        var titleFormat = state.fileFormat

        if ("{DEFAULT}" in titleFormat) {
            titleFormat = titleFormat.replace("{DEFAULT}", defaultTitle)
        }
        if ("{SIMPLE}" in titleFormat) {
            titleFormat = titleFormat.replace("{SIMPLE}", file.name)
        }
        return titleFormat
    }

    override fun getFileTitle(project: Project, file: VirtualFile): String {
        var title = state.fileFormat
        if ("{DEFAULT}" in title) {
            title = title.replace("{DEFAULT}", super.getFileTitle(project, file))
        }
        if ("{SIMPLE}" in title) {
            title = title.replace("{SIMPLE}", file.name)
        }
        return title
    }
}