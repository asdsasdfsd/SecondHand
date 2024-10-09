import { createRouter, createWebHistory } from 'vue-router';
import routes from '@/router';

describe('Router', () => {
    let router;

    beforeEach(() => {
        router = createRouter({
            history: createWebHistory(),
            routes,
        });
    });

    it('should navigate to login page', async () => {
        await router.push('/login');

        // 确保页面组件渲染
        expect(router.currentRoute.value.name).toBe('login');
    });

    it('should navigate to register page', async () => {
        await router.push('/register');

        expect(router.currentRoute.value.name).toBe('register');
    });

    it('should navigate to user home page', async () => {
        await router.push('/user');

        expect(router.currentRoute.value.name).toBe('user');
    });

    it('should navigate to admin management page', async () => {
        await router.push('/admin');

        expect(router.currentRoute.value.name).toBe('admin');
    });

    it('should navigate to admin user pagination component', async () => {
        await router.push('/admin/users');

        expect(router.currentRoute.value.name).toBe('admin-userpage');
    });

    it('should redirect from root to login page', async () => {
        await router.push('/');

        expect(router.currentRoute.value.name).toBe('login');
    });
});
