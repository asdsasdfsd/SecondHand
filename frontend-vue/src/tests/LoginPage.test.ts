// LoginPage.test.ts
import { mount } from '@vue/test-utils';
import { describe, it, expect, vi } from 'vitest';
import LoginPage from '../pages/LoginPage.vue';
import { createRouter, createWebHistory } from 'vue-router';

const router = createRouter({
    history: createWebHistory(),
    routes: [{ path: '/admin', name: 'Admin' }],
});

describe('LoginPage.vue', () => {
    it('renders the login form', () => {
        const wrapper = mount(LoginPage, {
            global: {
                plugins: [router],
            },
        });

        expect(wrapper.find('h2').text()).toBe('Login');
        expect(wrapper.find('input[type="text"]').exists()).toBe(true);
        expect(wrapper.find('input[type="password"]').exists()).toBe(true);
        expect(wrapper.find('button[type="submit"]').exists()).toBe(true);
    });

    it('shows an alert on invalid credentials', async () => {
        window.alert = vi.fn();

        const wrapper = mount(LoginPage, {
            global: {
                plugins: [router],
            },
        });

        await wrapper.find('input[type="text"]').setValue('user');
        await wrapper.find('input[type="password"]').setValue('wrongpass');
        await wrapper.find('form').trigger('submit.prevent');

        expect(window.alert).toHaveBeenCalledWith('Invalid username or password');
    });

    it('redirects to /admin on valid credentials', async () => {
        const push = vi.spyOn(router, 'push'); // 监听路由跳转

        const wrapper = mount(LoginPage, {
            global: {
                plugins: [router],
            },
        });

        await wrapper.find('input[type="text"]').setValue('admin');
        await wrapper.find('input[type="password"]').setValue('admin');
        await wrapper.find('form').trigger('submit.prevent');

        expect(push).toHaveBeenCalledWith('/admin');
    });
});
